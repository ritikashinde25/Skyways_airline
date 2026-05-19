import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';
 
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
 
  user = {
    username: '',
    password: ''
  };
 
  message = '';
  isError = false;
 
  constructor(private authService: AuthService, private router: Router) {}
 
  onLogin() {
    if (!this.user.username || !this.user.password) {
      this.message = 'Please fill in all fields!';
      this.isError = true;
      return;
    }
 
    this.authService.login(this.user).subscribe({
      next: (response) => {
        if (response.includes('Login successful')) {
          localStorage.setItem('username', this.user.username);
          this.message = 'Login successful! Redirecting...';
          this.isError = false;
          setTimeout(() => this.router.navigate(['/flights']), 1000);
        } else {
          this.message = response;
          this.isError = true;
        }
      },
      error: (err) => {
        this.message = 'Login failed. Please try again!';
        this.isError = true;
      }
    });
  }
}
 