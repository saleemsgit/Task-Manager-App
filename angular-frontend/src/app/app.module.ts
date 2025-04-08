import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // ✅ include ReactiveFormsModule
import { TaskComponent } from './task/task.component';
import { LoginComponent } from './login/login.component'; // ✅ make sure you add your LoginComponent here
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent,
    LoginComponent, // ✅ declare LoginComponent here
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule // ✅ add ReactiveFormsModule here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
