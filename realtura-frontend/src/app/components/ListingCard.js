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
            return; // Skip the initial render
        }
        const updateStatus = async () => {
            console.log('test1');

            try {
                const response = await fetch(`http://localhost:8081/api/v1/listings/update/${listing.id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({isActive: status, userId:userId}),
                });
                console.log('test2');

                const res = await response.json();
                console.log('resjson:', JSON.stringify(res, null, 2)); // Improved logging

                if (response.ok && res.status === 'SUCCESS') {
                    console.log('test3');
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
        console.log('handleStatus:');
        setStatus(prevStatus => !prevStatus);
    };

    const handleEdit = async () => {
        console.log('handleEdit:');
        router.push(`/listings/update/${listing.id}`);
    };

    const handleDelete = async () => {
        try {
            console.log('Success2:');
            const response = await fetch('http://localhost:8081/api/v1/listings/delete', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({id:listing.id, userId:1}),
            });
            console.log('Success3');

            const res = await response.json();
            console.log('resjson:', JSON.stringify(res, null, 2)); // Improved logging

            if (response.ok) {
                console.log('ok');
                if (res.status === 'SUCCESS') {
                    console.log('success');
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
                <div style={{ display: 'flex', justifyContent: 'center', gap: '10px', marginTop: '10px' }}>
                    <button onClick={handleEdit}>Edit</button>
                    <button onClick={handleDelete}>Delete</button>
                    <button onClick={handleStatus}>{status ? 'Active' : 'Inactive'}</button>
                </div>
            </Card>
        </div>
    );
};

export default ListingCard;
