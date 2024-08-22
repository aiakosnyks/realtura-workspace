'use client';
import { Card, CardBody, CardHeader } from "@nextui-org/card";
import Image from 'next/image';
import {useEffect, useState} from "react";
import { toast } from "react-toastify";
import {useRouter} from "next/navigation";
import {message} from "antd";
import {useAuth} from "@/app/context/AuthContext";

const ListingCard = ({ listing }) => {
    const [status, setStatus] = useState(listing.isActive);
    const [isInitialRender, setIsInitialRender] = useState(true);

    const { country, city, state } = listing.address;
    const router = useRouter();
    const { userId } = useAuth();

    const location = `${country.toUpperCase()}, ${city}, ${state}`;

    useEffect(() => {
        if (isInitialRender) {
            setIsInitialRender(false);
            return;
        }
        const updateStatus = async () => {
            try {
                const response = await fetch(`http://localhost:8081/api/v1/listings/update/${listing.id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        'Cache-Control': 'no-cache'
                    },
                    body: JSON.stringify({isActive: status, userId: userId}),
                });

                const res = await response.json();

                if (response.ok && res.status === 'SUCCESS') {
                    message.success('Listing updated successfully!');
                    router.push('/listings/mylistings');
                } else {
                    message.error('Update failed: ' + (res.message || 'Unknown error'));
                }
            } catch (error) {
                message.error('An error occurred: ' + (error.message || 'Unknown error'));
            }
        };
        updateStatus();
    }, [status]);

    const handleStatus = async (e) => {
        e.preventDefault();
        e.stopPropagation();
        setStatus(prevStatus => !prevStatus);
    };

    const handleEdit = async (e) => {
        e.stopPropagation();
        router.push(`/listings/update/${listing.id}`);
    };

    const handleDelete = async (e) => {
        e.stopPropagation();
        try {
            const response = await fetch('http://localhost:8081/api/v1/listings/delete', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Cache-Control': 'no-cache'
                },
                body: JSON.stringify({id: listing.id, userId: userId}),
            });

            const res = await response.json();

            if (response.ok) {
                if (res.status === 'SUCCESS') {
                    router.push('/listings');
                    router.refresh();
                    toast.success('Listing deleted successfully!');
                } else {
                    toast.error('Deletion failed: ' + (res.message || 'Unknown error'));
                }
            } else {
                toast.error('Deletion failed: ' + (res.message || 'Unknown error'));
            }
        } catch (error) {
            toast.error('An error occurred: ' + (error.message || 'Unknown error'));
        }
    };

    const handleClick = () => {
        router.push(`/listings/${listing.id}`);
    };

    const handleDetail = () => {
        router.push(`/listings/detail/${listing.id}`);
    };

    return (
        <div style={{ display: "flex" }}>
            <div onClick={handleClick} style={{ cursor: 'pointer', width: '100%' }}>
                <Card className="py-4" style={{ width: '100%' }}>
                    <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
                        <p className="text-tiny uppercase font-bold">{location}</p>
                        <small className="text-default-500">{listing.price}</small>
                        <h4 className="font-bold text-large">{listing.title}</h4>
                    </CardHeader>
                    <CardBody className="overflow-visible py-2">
                        <Image
                            onClick={handleDetail}
                            alt="Card background"
                            className="object-cover rounded-xl"
                            src={listing.photo}
                            width={270}
                            height={180}
                            priority={true}
                        />
                    </CardBody>
                    <div style={{ display: 'flex', justifyContent: 'center', gap: '10px', marginTop: '10px' }}>
                        <button onClick={handleEdit}>Edit</button>
                        <button onClick={handleDelete}>Delete</button>
                        <button onClick={handleDetail}>Detail</button>
                        <button onClick={handleStatus}>{status ? 'Active' : 'Inactive'}</button>
                    </div>
                </Card>
            </div>
        </div>
    );
};

export default ListingCard;
