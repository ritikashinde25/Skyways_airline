import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { FlightService } from '../../services/flight';
import { BookingService } from '../../services/booking';
 
@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './booking.html',
  styleUrl: './booking.css'
})
export class BookingComponent implements OnInit {
 
  flight: any = null;
  selectedClass = 'Economy';
  selectedSeat = 'Middle';
  selectedTime = '';
  basePrice = 0;
  totalPrice = 0;
 
  booking: any = {
    username: localStorage.getItem('username') || '',
    flightId: '',
    flightNumber: '',
    origin: '',
    destination: '',
    bookingDate: '',
    totalPrice: 0,
    travelClass: '',
    seatType: ''
  };
 
  message = '';
  isError = false;
  isSuccess = false;
 
  classes = ['Economy', 'Premium Economy', 'Business'];
  seatTypes = ['Middle', 'Aisle', 'Window'];
 
  classMultipliers: any = {
    'Economy': 1,
    'Premium Economy': 1.5,
    'Business': 2.5
  };
 
  seatCharges: any = {
    'Middle': 0,
    'Aisle': 500,
    'Window': 1000
  };
 
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private flightService: FlightService,
    private bookingService: BookingService
  ) {}
 
  ngOnInit() {
    const flightId = this.route.snapshot.paramMap.get('flightId');
    if (flightId) {
      this.flightService.getFlightById(flightId).subscribe({
        next: (data) => {
          this.flight = data;
          this.basePrice = data.price;
          this.selectedTime = data.departureTimes ?
            data.departureTimes[0] : data.departureTime;
          this.booking.flightId = data.id.toString();
          this.booking.flightNumber = data.flightNumber;
          this.booking.origin = data.origin;
          this.booking.destination = data.destination;
          this.booking.bookingDate = new Date()
            .toISOString().split('T')[0];
          this.calculatePrice();
        },
        error: () => {
          this.message = 'Flight not found!';
          this.isError = true;
        }
      });
    }
  }
 
  calculatePrice() {
    const classPrice = this.basePrice *
      this.classMultipliers[this.selectedClass];
    const seatExtra = this.seatCharges[this.selectedSeat];
    this.totalPrice = Math.round(classPrice + seatExtra);
    this.booking.totalPrice = this.totalPrice;
    this.booking.travelClass = this.selectedClass;
    this.booking.seatType = this.selectedSeat;
  }
 
  onBook() {
    this.bookingService.createBooking(this.booking).subscribe({
      next: (response) => {
        this.message = 'Booking created! Redirecting to payment...';
        this.isError = false;
        this.isSuccess = true;
        setTimeout(() => {
          this.router.navigate(['/payment', response.id]);
        }, 1000);
      },
      error: () => {
        this.message = 'Booking failed. Please try again!';
        this.isError = true;
      }
    });
  }
 
  goBack() {
    this.router.navigate(['/flights']);
  }
}

 