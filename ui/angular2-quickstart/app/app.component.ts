import { Component } from '@angular/core';

import {CoursesComponent} from './components/courses.component'

@Component({
  selector: 'my-app',
  template: `<h1>My First Angular 2 App</h1>
  				<courses></courses>`,

  providers: [CoursesComponent]
})
export class AppComponent { }
