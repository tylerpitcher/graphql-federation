import { Component, OnInit } from '@angular/core';

import { Apollo, gql } from 'apollo-angular';
import { CommonModule } from '@angular/common';

const STUDENTS_QUERY = gql`
  query {
    students {
      id
      firstName
      lastName
    }
  }
`;

@Component({
  selector: 'app-students',
  imports: [CommonModule],
  templateUrl: './students.component.html',
})
export class StudentsComponent implements OnInit {
  students: any[] = [];
  error: any;

  constructor(private apollo: Apollo) {

  }

  ngOnInit(): void {
    this.apollo.watchQuery({ query: STUDENTS_QUERY })
      .valueChanges.subscribe(({ data, error }: any) => {
        this.students = data.students;
        this.error = error;
      });
  }

}
