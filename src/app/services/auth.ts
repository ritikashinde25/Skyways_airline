import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
 
@Injectable({
  providedIn: 'root'
})
export class AuthService {
 
  private baseUrl = 'http://localhost:8084/api/auth';
 
  constructor(private http: HttpClient) {}
 
  register(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user,
      { responseType: 'text' });
  }
 
  login(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, user,
      { responseType: 'text' });
  }
 
  isLoggedIn(): boolean {
    return localStorage.getItem('username') !== null;
  }
 
  logout(): void {
    localStorage.removeItem('username');
  }
 
  getUsername(): string {
    return localStorage.getItem('username') || '';
  }
}
 