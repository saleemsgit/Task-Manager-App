import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TaskComponent } from './task/task.component';

const routes: Routes = [
  // {path: 'employees', component: EmployeeListComponent},
  // {path: 'create-employee', component: CreateEmployeeComponent},
  // {path: '', redirectTo: 'employees', pathMatch: 'full'},
  // {path: 'update-employee/:id', component: UpdateEmployeeComponent},
  // {path: 'employee-details/:id', component: EmployeeDetailsComponent},
  {path: 'login',component: LoginComponent},
  {path: 'register',component: RegisterComponent},
  
  {path: 'task',component: TaskComponent},
  {path: '', redirectTo: 'register', pathMatch: 'full'},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],                                                                                                                                                                                                                                                                                                          
  exports: [RouterModule]
})
export class AppRoutingModule { }
