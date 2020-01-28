import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { FlexLayoutModule } from "@angular/flex-layout";
import { LayoutModule } from "@angular/cdk/layout";
import { MatCardModule } from "@angular/material/card";
import { HomeComponent } from "./home/home.component";
import { ContinentOverviewComponent } from "./continent-overview/continent-overview.component";
import { CountryDetailsComponent } from "./country-details/country-details.component";
import { CountryEditorComponent } from "./country-editor/country-editor.component";

const routes: Routes = [
  { path: "home", component: HomeComponent },
  { path: "continent/:name", component: ContinentOverviewComponent },
  { path: "country/:name", component: CountryDetailsComponent },
  { path: "country-editor/:name", component: CountryEditorComponent },
  { path: "**", redirectTo: "home" }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    FlexLayoutModule,
    LayoutModule,
    MatCardModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
