import {Component} from '@angular/core'
import {CourseService} from '../services/course.service'
import { MdButtonModule } from '@angular2-material/button';
import { MdCardModule } from '@angular2-material/card';

@Component({
	selector: "courses",
	templateUrl: './app/components/courses.component.html',
	providers: [CourseService, MdButtonModule]
})
export class CoursesComponent {
	title: string = "The title of courses page";
	courses: string[];

	constructor(courseService: CourseService) {
		courseService.getCourses().then(courses => this.courses = courses);
	}
}