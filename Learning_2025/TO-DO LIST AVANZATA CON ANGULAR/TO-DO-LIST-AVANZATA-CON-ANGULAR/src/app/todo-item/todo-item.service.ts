import { Injectable } from '@angular/core';
import {TodoitemsService} from '../shared/todoitems.service.ts.service';

@Injectable({
  providedIn: 'root'
})
export class TodoItemService {

  constructor(private todoItemsService: TodoitemsService) { }

  
  updateDone(id: number, done: boolean) {
    //return this.todoItemsService.doHttpRequestDoneUpdate("itemController", id, done);
    return this.todoItemsService.doHttpRequest_UNICA("PUT", "itemController/updateDone", {id: id , done: done}, undefined, undefined, true);
  }

  /**
   * 
   * @param table la path della tabella 
   * @param id l'id dell'oggetto da eliminare viene passato nella path della richiesta (in spring è un parametro @RequestParm) 
   * @returns la promise della richiesta http
   */
  deleteItemQuery(id: number) {
    return this.todoItemsService.doHttpRequest_UNICA("DELETE", "itemController/deleteItem", id, undefined,undefined, true);
  }
}

