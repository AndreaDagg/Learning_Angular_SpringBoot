import { Injectable } from '@angular/core';
import { TodoitemsService } from '../shared/todoitems.service.ts.service';
import type { TodoItemNoID } from   '../shared/todo-item-noID.model';

@Injectable({
  providedIn: 'root'
})
export class TodoFormService {

  constructor(private todoItemsService:TodoitemsService ) { }

  addNewItemQuery(item: TodoItemNoID){
    return this.todoItemsService.doHttpRequest_UNICA(
      "POST",
      "itemController/addNewItem",
      item,
      undefined,
      undefined,
      true
    )
  }
}
