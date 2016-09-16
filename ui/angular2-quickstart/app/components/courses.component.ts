import {Component} from '@angular/core'
import {CourseService} from '../services/course.service'

import { MdUniqueSelectionDispatcher } from '@angular2-material/core';
import { MdButtonModule } from '@angular2-material/button';
import { MdCardModule } from '@angular2-material/card';
import { MdCheckboxModule } from '@angular2-material/checkbox';
import { MdIconRegistry } from '@angular2-material/icon';

@Component({
	selector: "courses",
	templateUrl: './app/components/courses.component.html',
	providers: [CourseService, MdButtonModule, MdUniqueSelectionDispatcher],
	viewProviders: [MdIconRegistry],
})
export class CoursesComponent {
	title: string = "The title of courses page";
	courses: string[];

	constructor(courseService: CourseService) {
		courseService.getCourses().then(courses => this.courses = courses);
	}
}