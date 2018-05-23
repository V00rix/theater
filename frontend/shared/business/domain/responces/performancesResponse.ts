import {Session} from '../session';
import {Performance} from '../performance';
import {SeatStatus} from '../enumeration/seatStatus.enum';

import {DateMapper} from '../dateMapper';

export class PerformancesResponse extends DateMapper{
    public performances: {
        id: number,
        title: string,
        image_url: string,
        description: string,
        sessions: {
            id: number,
            date: string,
            hall, number,
            seats: string[][]
        }[]
    }[];

    public maximum_seats: number;

    public static map(performancesResponse: PerformancesResponse): Performance[] {
        return performancesResponse.performances.map(
            p => {
                return new Performance(p.id, p.title, p.image_url, p.description, p.sessions.map(
                    s => {
                        return s ? new Session(s.id, DateMapper.mapDate(s.date), s.seats ? s.seats.map(
                            r => {
                                return r ? r.map(
                                    seat => {
                                        return {
                                            status: seat === 'FREE' ? SeatStatus.FREE :
                                                seat === 'HIDDEN' ? SeatStatus.HIDDEN : SeatStatus.BOOKED
                                        };
                                    }) : [];
                            }).reverse() : null) : null;
                    }));
            }
        );
    }
}
