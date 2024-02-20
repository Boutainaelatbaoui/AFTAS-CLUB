import { CanActivateFn } from '@angular/router';

export const superAdminGuardGuard: CanActivateFn = (route, state) => {
  return true;
};
