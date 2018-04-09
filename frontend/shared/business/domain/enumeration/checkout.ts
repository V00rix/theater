export namespace Checkout {
    export enum Code {
        DELIVERY = 'delivery',
        SELF = 'self',
        PAY_BEFORE = 'payBefore'
    }

    export function map(checkout: string) {
        return checkout === 'SELF_CHECKOUT' ? Checkout.Code.SELF :
            checkout === 'DELIVERY' ? Checkout.Code.DELIVERY : Checkout.Code.PAY_BEFORE;
    }

    export function toBe(checkout: Checkout.Code) {
        return checkout === Checkout.Code.SELF ? 'SELF_CHECKOUT' :
            checkout === Checkout.Code.DELIVERY ? 'DELIVERY' : 'PAY_BEFORE';
    }
}
