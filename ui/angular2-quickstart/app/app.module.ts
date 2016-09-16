import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MdButtonModule } from '@angular2-material/button';
import { MdCardModule } from '@angular2-material/card';

import { AppComponent }   from './app.component';
import { CoursesComponent }   from './components/courses.component';

@NgModule({
  imports:      [ BrowserModule, MdButtonModule, MdCardModule ],
  declarations: [ AppComponent, CoursesComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
