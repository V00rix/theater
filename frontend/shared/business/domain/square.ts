export class Square {
    constructor(public width: number, public height: number) {
    }

    /**
     * Checks if square is in boundaries
     * @param {number} mw Width constraint
     * @param {number} mh Height constraint
     * @returns {boolean}
     */
    public isBig(mw: number, mh: number): boolean {
        return this.width > mw && this.height >= mh;
    }

    /**
     * Checks if square is in boundaries
     * @param {Square} constraint Constraint square
     * @returns {boolean}
     */
    public isBigC(constraint: Square): boolean {
        return this.width > constraint.width && this.height >= constraint.height;
    }
}
