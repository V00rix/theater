import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {DataService} from '../../business/services/data.service';
import {MessagesComponent} from '../../business/components/messages/messages.component';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.scss']
})
export class AuthorizationComponent implements OnInit {
  public loginFC = new FormControl('');
  public passwordFC = new FormControl('');

  public login: string;
  public password: string;

  constructor(public data: DataService, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.data.postSubmit(this.login, this.password).subscribe(() => {
      this.router.navigate(['/home']);
    }, () => {
      console.log(this.data.currentErrors);
    });
  }
}
