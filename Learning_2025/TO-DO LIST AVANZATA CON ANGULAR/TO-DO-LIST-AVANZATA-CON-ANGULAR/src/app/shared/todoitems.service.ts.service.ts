import { EventEmitter, Injectable, Output, signal, Input } from '@angular/core';
import type { TodoItem } from './todo-item.model';
import {TodoItemFilterDto} from './todo-item-filter-dto';
import { HttpClient, HttpRequest, HttpResponse } from '@angular/common/http';




@Injectable({
  providedIn: 'root',
})
export class TodoitemsService {
  //private todoItems: TodoItem[] = [];
  todoItems = signal<TodoItem[]>([]);

  @Output() todoItemsListModify = new EventEmitter<void>();

  private isEnglish: boolean = false;

  constructor(private httpClient: HttpClient) {}

  public static formFilterValidators(dataParam: TodoItemFilterDto): TodoItemFilterDto {
    let formFilterValidators = {
      'title': dataParam.title || "", 
      'description': dataParam.description || "",
      'done': dataParam.done || undefined,
      'category': dataParam.category || "",
      'isEnglish': dataParam.isEnglish || undefined, 
      'id': dataParam.id !== null ? dataParam.id : undefined
    };
    return formFilterValidators;
    
  }

  /**
   *  - - - - CHIAMATA GENERICA - - - -
   *
   * @param paramMethod il metodo della richiesta http (GET, POST, PUT, DELETE)
   * @param urlEndpoint endpoint della richiesta che viene catturata dal controller (in spring è una path @RequestMapping)
   * @param dataParam [facoltativo] i parametri della richiesta http (in spring è un parametro @RequestBody)
   * @param headers [facoltativo] gli headers della richiesta http
   * @param paramResponseType [facoltativo] il tipo di risposta della richiesta http (arraybuffer, blob, json, text)
   * @param doUpdate [facoltativo] booleano che indica se la schermata deve essere aggiornata dopo la chiamata, invia un segnale all'evento todoItemsListModify emitter
   * @returns la promise della richiesta http
   */
  public doHttpRequest_UNICA(
    paramMethod: string,
    urlEndpoint: string,
    dataParam?: Object,
    headers?: Array<{ key: string; value: string }>,
    paramResponseType?: 'arraybuffer' | 'blob' | 'json' | 'text',
    doUpdate?: boolean
  ): Promise<any> {
    console.log('SERVICE GENERAL => doHttpRequest_UNICA');

    let host: string = 'http://localhost:8080/api/';

    let uri = host + urlEndpoint;

    /* if (this.userAgentSrv.browser.toUpperCase() === 'IE') {
      if (uri.indexOf('?') !== -1) {
        uri += '&cache=' + new Date().getTime();
      } else {
        uri += '?cache=' + new Date().getTime();
      }
 
    }
    if (environment.mock) {
      uri += '.json';
      paramMethod = 'GET';
    } */

    let httpRequest = new HttpRequest(paramMethod, uri, dataParam, {
      responseType: paramResponseType || 'json',
    });

    if (headers && headers.length > 0) {
      headers.map((item) => {
        httpRequest = httpRequest.clone({
          headers: httpRequest.headers.set(item.key, item.value),
        });         
      });
    } else {
      httpRequest = httpRequest.clone({
        headers: httpRequest.headers.set('Content-Type', 'application/json'),
      });
    }

    console.log('SERVICE GENERAL => doHttpRequest_UNICA => httpRequest', httpRequest, 'dataParam', dataParam);
    return new Promise((resolve, reject) => {
      const rq = this.httpClient.request(httpRequest);
      if (rq) {
        rq.toPromise().then(
          (res) => {
            // Success
            resolve(res);
            //Se la chiamata è andata a buon fine, notifico il cambiamento che aggiorna la schermata
            if (doUpdate) {
              this.todoItemsListModify.emit();
            }
          },
          (msg) => {
            // Error
            reject(msg);
          }
        );
      }
    });
  }

  setLanguage(isEnglish: boolean) {
    this.isEnglish = isEnglish; // Aggiorna il valore della lingua
    this.todoItemsListModify.emit(); // Notifica il cambiamento
  }

  getLanguage() {
    return this.isEnglish;
  }
}
