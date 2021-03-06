import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from './../auth/auth.service';
import { Observable } from 'rxjs/Observable';
import { GoldcardService } from '../goldcard.service';

@Component({
  selector: 'app-userview',
  templateUrl: './userview.component.html',
  styleUrls: ['./userview.component.css']
})
export class UserviewComponent implements OnInit {
  private loggedIn = new BehaviorSubject<boolean>(false); // {1}
  isLoggedIn$: Observable<boolean>;
  username : string;
  accs : Array<any>;
  firstname : string;
  members : Array<any>;

  constructor(private authService: AuthService,private goldcardService: GoldcardService) {
  }

   get isLoggedIn() {
    return this.loggedIn.asObservable(); // {2}
  }

  ngOnInit() {
      this.isLoggedIn$ = this.authService.isLoggedIn;
      this.username = localStorage.getItem('currentUser');
      this.goldcardService.getAccepted().subscribe(data =>{
          this.accs = data;
          for(let i of this.accs){
               if(i.rightRegistration.username == this.username){
                     this.members = i;
                     console.log(i);
               }
          }
      });
  }
    onLogout(){
          alert("Goodbye "+localStorage.getItem('currentUser'));
          this.authService.logout();                      // {3}
    }

}
