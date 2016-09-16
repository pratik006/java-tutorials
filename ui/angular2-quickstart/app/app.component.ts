import { Component } from '@angular/core';

import { MdIconRegistry } from '@angular2-material/icon';

import {CoursesComponent} from './components/courses.component'

@Component({
  selector: 'my-app',
  templateUrl: './app/app.component.html',
  styleUrls: ['./app/app.component.css'],
  providers: [CoursesComponent, MdIconRegistry],
})
export class AppComponent {
	views: Object[] = [
    {
      name: "My Account",
      description: "Edit my account information",
      icon: "assignment ind"
    },
    {
      name: "Potential dates",
      description: "Find your soulmate!",
      icon: "pets"
    }
  ];
}
