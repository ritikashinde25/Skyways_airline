import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
 
@Injectable({
  providedIn: 'root'
})
export class PaymentService {
 
  private baseUrl = 'http://localhost:8087/api/payments';
 
  constructor(private http: HttpClient) {}
 
  processPayment(payment: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/process`, payment);
  }
 
  getPaymentsByUsername(username: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${username}`);
  }

  createPaymentIntent(amount: number, bookingId: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/create-payment-intent`, {
      amount: amount,
      bookingId: bookingId.toString()
    });
  }
  
}