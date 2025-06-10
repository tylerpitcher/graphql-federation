import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StudentsComponent } from './students/students.component';
import { CoursesComponent } from './courses/courses.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CoursesComponent, StudentsComponent],
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = 'school-client';
}
