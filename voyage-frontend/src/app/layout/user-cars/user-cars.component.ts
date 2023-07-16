import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CarService} from "../../services/CarService";
import {BehaviorSubject, Observable, switchMap, take} from "rxjs";
import {Car} from "../../models/Car";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-user-cars',
  templateUrl: './user-cars.component.html',
  styleUrls: ['./user-cars.component.scss']
})
export class UserCarsComponent implements OnInit{

  userForm!: FormGroup

  public readonly userCars$: Observable<Car[]>;

  private subject$: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);

  constructor(private fb: FormBuilder,
              private carService: CarService) {
    this.userCars$ = this.subject$.pipe(
      switchMap(() => this.carService.getUserCars())
    )
  }

  ngOnInit() {
    this.userForm = this.fb.group({
      mark: ['', Validators.required],
      color: ['', Validators.required],
      licensePlate: ['', Validators.required]
    });
  }

  onSubmit() {
    this.carService.addCar(this.userForm)
      .pipe(take(1))
      .subscribe(() => this.subject$.next());
  }

  deleteCar(id: number) {
    this.carService.deleteCar(id)
      .pipe(take(1))
      .subscribe(() => this.subject$.next())
  }

  protected readonly faTrash = faTrash;
}
