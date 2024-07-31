'use client';
import { Card, CardBody, CardHeader } from "@nextui-org/card";
import Image from 'next/image';
import { useState } from "react";
import { toast } from "react-toastify";
import {useRouter} from "next/navigation";

const ListingCardReadOnly = ({ listing }) => {
    const { country, city, state } = listing.address;

    const location = `${country.toUpperCase()}, ${city}, ${state}`;
    console.log(listing);

    return (
        <div style={{ display: "flex" }}>
            <Card className="py-4" style={{ width: '100%' }}>
                <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
                    <p className="text-tiny uppercase font-bold">{location}</p>
                    <small className="text-default-500">{listing.price}</small>
                    <h4 className="font-bold text-large">{listing.title}</h4>
                </CardHeader>
                <CardBody className="overflow-visible py-2">
                    <Image
                        alt="Card background"
                        className="object-cover rounded-xl"
                        src={listing.photo}
                        width={270}
                        height={180}
                        priority={true}  // Add priority property here
                    />
                </CardBody>
            </Card>
        </div>
    );
};

export default ListingCardReadOnly;
