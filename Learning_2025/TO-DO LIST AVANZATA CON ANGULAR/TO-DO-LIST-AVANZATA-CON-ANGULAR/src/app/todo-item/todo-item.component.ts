import {
  Component,
  Input,
  AfterViewChecked,
  Output,
  EventEmitter,
  OnInit,
} from '@angular/core';
import type { TodoItem } from '../shared/todo-item.model';
import type { filterActive } from '../filters/filters.model';
import { TodoItemService } from './todo-item.service';
import { ChangeDetectionStrategy } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-todo-item',
  standalone: true,
  imports: [MatCardModule, MatCheckboxModule, FormsModule, CommonModule],
  templateUrl: './todo-item.component.html',
  styleUrl: './todo-item.component.css',
})
export class TodoItemComponent implements OnInit {
  @Input({ required: true }) todoItem!: TodoItem;
  //@Output() delete = new EventEmitter<string>();


  deleteCheckedView = true;

  constructor(private todoItemService: TodoItemService) {}

  ngOnInit() {
    if (this.todoItem.done == true) {
      this.deleteCheckedView = false;
    }
  }

  onDone() {
    //this.todoitemsService.setIfDone(this.todoItem.id, !this.todoItem.done);
    this.todoItemService.updateDone(this.todoItem.id, !this.todoItem.done);
    this.deleteCheckedView = !this.deleteCheckedView;

  }

  onDelete() {
    if (this.todoItem.done == true) {
      // this.todoitemsService.deleteTodoItem(this.todoItem.id);
      //this.delete.emit(this.todoItem.id);
      this.todoItemService.deleteItemQuery(this.todoItem.id);
    } else {
      console.log('Non si può eliminare un task non completato');
    }
  }
}
