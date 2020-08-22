import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClasse } from 'app/shared/model/classe.model';
import { ClasseService } from './classe.service';
import { ClasseDeleteDialogComponent } from './classe-delete-dialog.component';
import {Account} from "app/core/user/account.model";
import {AccountService} from "app/core/auth/account.service";

@Component({
  selector: 'jhi-classe',
  templateUrl: './classe.component.html'
})
export class ClasseComponent implements OnInit, OnDestroy {
  classes?: IClasse[];
  eventSubscriber?: Subscription;

  account: Account | null = null;
  authSubscription?: Subscription;

  constructor(private accountService: AccountService, protected classeService: ClasseService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.classeService.query().subscribe((res: HttpResponse<IClasse[]>) => (this.classes = res.body || []));
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.loadAll();
    this.registerChangeInClasses();

  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClasse): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClasses(): void {
    this.eventSubscriber = this.eventManager.subscribe('classeListModification', () => this.loadAll());
  }

  delete(classe: IClasse): void {
    const modalRef = this.modalService.open(ClasseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.classe = classe;
  }
}
