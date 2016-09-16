import { Injectable } from '@angular/core';

@Injectable()
export class CourseService {
	getCourses():Promise<string[]> {
		return Promise.resolve(["Course1", "Course2", "Course2"]);
	}
}