import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from '../../services/flight';
 
@Component({
  selector: 'app-flight-search',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './flight-search.html',
  styleUrl: './flight-search.css'
})
export class FlightSearchComponent implements OnInit {
 
  origin = '';
  destination = '';
  flights: any[] = [];
  allFlights: any[] = [];
  message = '';
  searched = false;
  username = localStorage.getItem('username') || '';
 
  constructor(private flightService: FlightService, private router: Router) {}
 
  ngOnInit() {
    this.flightService.getAllFlights().subscribe({
      next: (data) => {
        this.allFlights = data;
        this.flights = data;
      },
      error: () => {
        this.message = 'Failed to load flights!';
      }
    });
  }
 
  onSearch() {
    if (!this.origin || !this.destination) {
      this.message = 'Please enter origin and destination!';
      return;
    }
    this.searched = true;
    this.flightService.searchFlights(this.origin, this.destination).subscribe({
      next: (data) => {
        this.flights = data;
        this.message = '';
      },
      error: () => {
        this.flights = [];
        this.message = 'No flights found!';
      }
    });
  }
 
  onClear() {
    this.origin = '';
    this.destination = '';
    this.flights = this.allFlights;
    this.message = '';
    this.searched = false;
  }
 
  onBook(flight: any) {
    this.router.navigate(['/booking', flight.id]);
  }
 
  goToMyBookings() {
    this.router.navigate(['/my-bookings']);
  }
 
  logout() {
    localStorage.removeItem('username');
    this.router.navigate(['/login']);
  }
}
 