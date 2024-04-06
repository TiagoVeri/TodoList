import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TaskListComponent } from './tasks/components/task-list/task-list.component';
import { AppMaterialModule } from './shared/app-material/app-material.module';
import { TodoComponent } from './tasks/container/todo/todo.component';



@NgModule({
  declarations: [
    AppComponent,
    TaskListComponent,
    TodoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
