import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth'; // Change if needed

  constructor(private http: HttpClient) {}

  register(email: string, fullName: string, password: string): Observable<any> {
    const body = { email, fullName, password };
    return this.http.post(`${this.baseUrl}/signup`, body);
  }

  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post(`${this.baseUrl}/login`, body);
  }
}
