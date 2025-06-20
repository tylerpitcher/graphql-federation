import { Component, OnInit } from '@angular/core';

import { Apollo, gql } from 'apollo-angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

const STUDENTS_QUERY = gql`
  query {
    students {
      id
      firstName
      lastName
    }
  }
`;

const CREATE_STUDENT_MUTATION = gql`
  mutation CreateStudent($firstName: String!, $lastName: String!, $age: Int!) {
    createStudent(firstName: $firstName, lastName: $lastName, age: $age) {
      id
      firstName
      lastName
    }
  }
`;

@Component({
  selector: 'app-students',
  imports: [CommonModule, FormsModule],
  templateUrl: './students.component.html',
})
export class StudentsComponent implements OnInit {
  students: any[] = [];
  error: any;
  firstName = '';
  lastName = '';
  age: number | null = null;
  loading = false;

  constructor(private apollo: Apollo) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {
    this.apollo.watchQuery({ query: STUDENTS_QUERY })
      .valueChanges.subscribe(({ data, error }: any) => {
        this.students = data.students;
        this.error = error;
      });
  }

  createStudent(): void {
    if (!this.firstName || !this.lastName || this.age == null) {
      this.error = 'All fields are required.';
      return;
    }

    this.loading = true;
    this.apollo.mutate({
      mutation: CREATE_STUDENT_MUTATION,
      variables: {
        firstName: this.firstName,
        lastName: this.lastName,
        age: this.age,
      },
    }).subscribe({
      next: ({ data }: any) => {
        const createdStudent = (data as any).createStudent;
        this.students.push(createdStudent);
        this.firstName = '';
        this.lastName = '';
        this.age = null;
        this.error = null;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message;
        this.loading = false;
      }
    });
  }
}

