import { Directive, Input } from '@angular/core';
import { Validator,NG_VALIDATORS, AbstractControl } from '@angular/forms';

@Directive({
  selector: '[appValidator]',
  providers: [{
  provide: NG_VALIDATORS,
  useExisting: ValidatorDirective,
  multi: true
  }]
})
export class ValidatorDirective implements Validator  {

  @Input() appConfirmEqualValidator: string;

  constructor() { }

  validate(control: AbstractControl):{[key:string]: any} |null {
    const controlToCompare = control.parent.get(this.appConfirmEqualValidator);
    if(controlToCompare && controlToCompare.value !==control.value){
        return { 'notEqual': true}
    }
     return null;
  }
}
