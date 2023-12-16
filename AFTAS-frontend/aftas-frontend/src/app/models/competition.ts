export interface Competition {
    id: number;
    code: string;
    date: string; 
    startTime: string;
    endTime: string;
    limitParticipants: number;
    numberOfParticipants: number;
    location: string;
    amount: number;
}
