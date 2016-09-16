import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { MdInputModule } from '@angular2-material/input';
import { MdButtonModule } from '@angular2-material/button';
import { MdCardModule } from '@angular2-material/card';
import { MdCheckboxModule } from '@angular2-material/checkbox';
import { MdRadioModule } from '@angular2-material/radio';
import { MdListModule } from '@angular2-material/list';
import { MdTabsModule } from '@angular2-material/tabs';
import { MdGridListModule } from '@angular2-material/grid-list';
import { MdButtonToggleModule } from '@angular2-material/button-toggle';
import { MdToolbarModule } from '@angular2-material/toolbar';
import { MdIconModule } from '@angular2-material/icon';

import { AppComponent }   from './app.component';
import { CoursesComponent }   from './components/courses.component';

@NgModule({
  imports:      [ BrowserModule, MdInputModule, MdButtonModule, MdCardModule, MdCheckboxModule, MdRadioModule, MdListModule, MdTabsModule, MdGridListModule, MdIconModule ],
  declarations: [ AppComponent, CoursesComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
