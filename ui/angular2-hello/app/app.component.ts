import {Component} from 'angular2/core';
import {CoursesComponent} from './components/courses.component'
import {AuthorsComponent} from './components/authors.component'
import {AutoGrowDirective} from './directives/auto-grow.directive'

@Component({
    selector: 'my-app',
    template: `<h1>My First Angular 2 App</h1>
    			<input id="abc" type="text" autoGrow />
    			<courses></courses><authors></authors>

    			`,
    directives: [CoursesComponent, AuthorsComponent, AutoGrowDirective]
})
export class AppComponent { }