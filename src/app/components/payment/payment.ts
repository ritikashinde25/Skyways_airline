import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { PaymentService } from '../../services/payment';
import { loadStripe, Stripe, StripeCardElement } from '@stripe/stripe-js';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './payment.html',
  styleUrl: './payment.css'
})
export class PaymentComponent implements OnInit {

  bookingId: any;
  payment: any = {
    username: localStorage.getItem('username') || '',
    bookingId: '',
    amount: 0,
    paymentMethod: 'CREDIT_CARD'
  };

  cardNumber = '';
  cardHolder = '';
  expiryDate = '';
  cvv = '';
  message = '';
  isError = false;
  isSuccess = false;
  isProcessing = false;

  // ✅ NEW Stripe variables
  private stripe: Stripe | null = null;
  private cardElement: StripeCardElement | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private paymentService: PaymentService
  ) {}

  async ngOnInit() {
    this.bookingId = this.route.snapshot.paramMap.get('bookingId');
    this.payment.bookingId = this.bookingId;
    this.payment.amount=50000;
    // ✅ Paste your pk_test_... key inside these quotes
    this.stripe = await loadStripe('pk_test_51TYOIXGSZQfOA0NVX3gZ7JHkClZTcqCe7i0hyq20ij3hcC08ePQvXnE3NtamrxDkoMNLpNvy6q03Pkcro33zNELV00Pfu0hNDJ');

    // ✅ Only mount Stripe card element for CREDIT/DEBIT card
    this.mountStripeCard();
  }

  mountStripeCard() {
    if (this.stripe && (
      this.payment.paymentMethod === 'CREDIT_CARD' ||
      this.payment.paymentMethod === 'DEBIT_CARD')) {

      setTimeout(() => {
        const elements = this.stripe!.elements();
        this.cardElement = elements.create('card', {
          style: {
            base: {
              fontSize: '16px',
              color: '#32325d',
              fontFamily: 'Arial, sans-serif',
              '::placeholder': { color: '#aab7c4' }
            },
            invalid: { color: '#fa755a' }
          }
        });
        this.cardElement.mount('#card-element');
      }, 100);
    }
  }

  onMethodChange() {
    // Remount Stripe card when user switches to card payment
    if (this.payment.paymentMethod === 'CREDIT_CARD' ||
        this.payment.paymentMethod === 'DEBIT_CARD') {
      this.mountStripeCard();
    } else {
      this.cardElement = null;
    }
  }

  async onPayment() {
    this.isProcessing = true;
    this.message = 'Processing payment...';
    this.isError = false;

    // CREDIT_CARD or DEBIT_CARD — use Stripe
    if (this.payment.paymentMethod === 'CREDIT_CARD' ||
        this.payment.paymentMethod === 'DEBIT_CARD') {

      if (!this.stripe || !this.cardElement) {
        this.message = 'Stripe not loaded. Please refresh.';
        this.isError = true;
        this.isProcessing = false;
        return;
      }

      // Step 1: Get clientSecret from Spring Boot
      this.paymentService.createPaymentIntent(
        this.payment.amount,
        this.payment.bookingId
      ).subscribe({
        next: async (response: any) => {
          const clientSecret = response.clientSecret;

          // Step 2: Confirm card payment via Stripe
          const result = await this.stripe!.confirmCardPayment(clientSecret, {
            payment_method: {
              card: this.cardElement!,
              billing_details: {
                name: this.payment.username
              }
            }
          });

          if (result.error) {
            this.isProcessing = false;
            this.message = result.error.message || 'Payment failed!';
            this.isError = true;
          } else if (result.paymentIntent.status === 'succeeded') {
            // Step 3: Save to your DB
            this.savePaymentToDB();
          }
        },
        error: () => {
          this.isProcessing = false;
          this.message = 'Could not connect to payment server!';
          this.isError = true;
        }
      });

    } else {
      // NET_BANKING or UPI — skip Stripe, save directly to DB
      if (!this.cardNumber) {
        this.isProcessing = false;
        this.message = 'Please fill in all required fields!';
        this.isError = true;
        return;
      }
      this.savePaymentToDB();
    }
  }

  savePaymentToDB() {
    this.paymentService.processPayment(this.payment).subscribe({
      next: () => {
        this.isProcessing = false;
        this.message = 'Payment successful! 🎉';
        this.isSuccess = true;
        this.isError = false;
        setTimeout(() => {
          this.router.navigate(['/confirmation', this.bookingId]);
        }, 1500);
      },
      error: () => {
        this.isProcessing = false;
        this.message = 'Payment failed. Please try again!';
        this.isError = true;
      }
    });
  }

  goBack() {
    this.router.navigate(['/flights']);
  }
}