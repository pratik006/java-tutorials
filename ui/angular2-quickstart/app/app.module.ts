import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }   from './app.component';
import { CoursesComponent }   from './components/courses.component';

@NgModule({
  imports:      [ BrowserModule ],
  declarations: [ AppComponent, CoursesComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
