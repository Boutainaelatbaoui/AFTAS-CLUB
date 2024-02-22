import { Role } from "./role";

export interface MemberResponse {
    id: number;
    num?: number;
    name: string;
    familyName: string;
    accessionDate?: string;
    nationality: string;
    identityDocument: string;
    identityNumber: string;
    role: Role,
    enabled: boolean
}
