import { Competition } from "./competition";
import { Fish } from "./fish";
import { Member } from "./member";

export interface Hunting {
    id?: number;
    numberOfFish: number;
    fishId: number;
    competitionId: number;
    weight: number;
    memberId: number;
    fish?: Fish;
    competition?: Competition;
    member?: Member;
}
