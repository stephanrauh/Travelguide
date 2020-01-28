import { Component, OnInit, Input } from "@angular/core";
import { Country } from "../backend/countries/country";
import { CountriesService } from "../backend/countries/countries.service";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-continent-overview",
  templateUrl: "./continent-overview.component.html",
  styleUrls: ["./continent-overview.component.scss"]
})
export class ContinentOverviewComponent implements OnInit {
  public countries: Array<Country>;

  @Input()
  public continent: string;

  constructor(
    private countriesService: CountriesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(parameters => {
      this.continent = parameters.get("name");
      this.loadCountries();
    });
  }

  private loadCountries(): void {
    this.countriesService
      .countriesByContinent(this.continent)
      .subscribe(result => {
        this.countries = result;
      });
  }
}
