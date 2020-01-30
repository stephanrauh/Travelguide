import { Component, OnInit, Input } from '@angular/core';
import { ContinentsService } from '../backend/continents/continents.service';

@Component({
  selector: 'app-continent',
  templateUrl: './continent.component.html',
  styleUrls: ['./continent.component.scss']
})
export class ContinentComponent implements OnInit {
  @Input()
  public continent: string;

  constructor() {}

  ngOnInit() {}

  public viewMore(): void {
    console.log('View more');
  }
}
