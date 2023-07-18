import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthSystemViewsComponent } from './auth-system-views.component';

describe('AuthSystemViewsComponent', () => {
  let component: AuthSystemViewsComponent;
  let fixture: ComponentFixture<AuthSystemViewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthSystemViewsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthSystemViewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
