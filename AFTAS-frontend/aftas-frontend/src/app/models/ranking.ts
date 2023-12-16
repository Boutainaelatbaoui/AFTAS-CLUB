import { Competition } from "./competition";
import { Member } from "./member";

export interface Ranking {
    rank: number;
    score: number;
    competition: Competition;
    member: Member;
}