import { Component, OnInit } from '@angular/core';

import { Apollo, gql } from 'apollo-angular';
import { CommonModule } from '@angular/common';

const COURSES_QUERY = gql`
  query {
    courses {
      id
      name
    }
  }
`;

@Component({
  selector: 'app-courses',
  imports: [CommonModule],
  templateUrl: './courses.component.html',
})
export class CoursesComponent implements OnInit {
  courses: any[] = [];
  error: any;

  constructor(private apollo: Apollo) {

  }

  ngOnInit(): void {
    this.apollo.watchQuery({ query: COURSES_QUERY })
      .valueChanges.subscribe(({ data, error }: any) => {
        this.courses = data.courses;
        this.error = error;
      });
  }

}
