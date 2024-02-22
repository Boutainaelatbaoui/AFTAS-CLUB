import {CanActivateFn, Router} from '@angular/router';
import { StorageService } from 'src/app/services/storage/storage.service';

export const authGuard: CanActivateFn = (
  route,
  state
) => {
  const router = new Router;
  const storageService = new StorageService;
  const user = localStorage.getItem('authenticated-user');
  console.log(storageService.getUserEnabled());
  console.log(storageService.getRoles());
  console.log(storageService.getPermissions());
  console.log(storageService.decodeToken());
  
  
  if(user && storageService.getUserEnabled() === true){
    
    
    return true;
  }
  else{
    router.navigate(['/login']);
    return false;
  }

}
