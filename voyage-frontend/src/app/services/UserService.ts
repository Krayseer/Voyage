import {Injectable} from "@angular/core";
import {Observable, switchMap, take} from "rxjs";
import {FormGroup} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {User} from "../models/User";

@Injectable()
export class UserService {

  constructor(private http: HttpClient,
              private router: Router) {
  }

  public registration(userForm: FormGroup) {
    const body = JSON.stringify(userForm.value);
    localStorage.removeItem('token');
    this.http.post<{ token: string }>(
      'api/auth/register', body, {headers: {'Content-Type': 'application/json'}}
    ).subscribe(next => {
        localStorage.setItem('token', next.token)
        this.router.navigate(['/cabinet']);
      }
    );
  }

  public authentication(userForm: FormGroup) {
    localStorage.removeItem('token');
    this.http.post<{ token: string }>('api/auth/authenticate', userForm.value, {headers: {'Content-Type': 'application/json'}})
      .pipe(take(1))
      .subscribe({
        next: next => {
          localStorage.setItem('token', next.token)
          this.router.navigate(['/cabinet']);
        }
      });
  }

  public getAccountInfo() {
    return this.http.get<User>("/api/account");
  }

  public uploadAccountPhoto(file: File) {
    const formData = new FormData();
    formData.append('photo', file);
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    return this.http.post('/api/account/photo', formData, { headers });
  }

  public getAccountPhoto(): Observable<string> {
    return this.http.get('/api/account/photo', { responseType: 'blob'})
      .pipe(
        switchMap((blob => {
          const reader = new FileReader();
          const image$ = new Observable<string>((subscriber) => {
            reader.onloadend = () => {
              subscriber.next(reader.result as string);
              subscriber.complete();
            };
          });
          reader.readAsDataURL(blob);
          return image$;
        }))
      )
  }

}
