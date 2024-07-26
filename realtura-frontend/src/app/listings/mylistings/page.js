'use client'

import ListingCard from "@/app/components/ListingCard";
import styles from './page.module.css'
import {useAuth} from "@/app/context/AuthContext";
import {useEffect, useState} from "react";
import {Button} from "antd";
import {router} from "next/client";
import {useRouter} from "next/navigation";
import {format, isValid} from "date-fns";
import {toast} from "react-toastify";

const page = 0;
const size = 10;


const Dashboard = () => {
    const { userId } = useAuth();
    const [listings, setListings] = useState([])
    const [subscriptions, setSubscriptions] = useState([])
    const router = useRouter();

    useEffect( () => {
        const fetchListings = async () => {
            if (userId == null) return;

            try {
                const response = await fetch('http://localhost:8081/api/v1/listings/getAllByFilter', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ page, size, userId: userId }),
                });
                const res =await response.json();
                setListings(res.data)
               // console.log('listings:', JSON.stringify(res.data, null, 2)); // Improved logging
            } catch (error) {
                console.error('Error fetching listings:', error);
            }
        };

        const fetchSubscriptions = async () => {
           // console.log('subscriptions1:', userId);
            if (userId == null) return;

            try {
                const response = await fetch(`http://localhost:8051/api/v1/subscriptions/${userId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });
             //   console.log('subscriptions2');

                const res =await response.json();
                setSubscriptions(res.data)
              //  console.log('subscription:', JSON.stringify(res.data, null, 2));
            } catch (error) {
                console.error('Error fetching listings:', error);
            }
        };
        fetchListings();
        fetchSubscriptions();
    }, [userId]);

    const addNewListing =() => {
        console.log('Create listing page routed');
        router.push("/listings/create");
    }

    const purchaseNewSubscription =() => {
        console.log('Purchase subscription page routed');
        router.push("/subscribe");
    }
    const formatDate = (dateArray) => {
        if (!Array.isArray(dateArray) || dateArray.length < 6) {
            return "Invalid date";
        }

        const [year, month, day, hour, minute, second, millisecond] = dateArray;
        const date = new Date(year, month - 1, day, hour, minute, second, millisecond / 1000000); // Adjust millisecond precision

        if (!isValid(date)) {
            return "Invalid date";
        }

        return format(date, 'yyyy-MM-dd HH:mm:ss'); // Adjust the format as needed
    };
    const checkCredits = () => {
        if (subscriptions.credits <= 0) return false;
        const dateArray = subscriptions.subscribedUntil;
        if (!Array.isArray(dateArray) || dateArray.length < 6) {
            return false;
        }
        const [year, month, day, hour, minute, second, millisecond] = dateArray;
        const date = new Date(year, month - 1, day, hour, minute, second, millisecond / 1000000);
        return date.getTime() >= Date.now();
    }
    return (
        <div className={styles.main}>
            <div className={styles.buttonContainer}>
                {subscriptions && checkCredits() && (
                    <Button onClick={addNewListing}>Add New Listing</Button>
                )}                <span></span>
                <Button onClick={purchaseNewSubscription}>Purchase</Button>
            </div>
            {subscriptions && <div className={styles.subscriptionInfo}>
                <p>Credits: {subscriptions.credits}</p>
                <p>Subscribed Until: {formatDate(subscriptions.subscribedUntil)}</p>
                <p>Subscription Duration: {subscriptions.subscriptionDuration}</p>
            </div> }
            <div className={styles.card}>
                {listings && listings.map((listing) => (
                    <ListingCard key={listing.id} listing={listing}/>
                ))}
            </div>

        </div>
    );
};

export default Dashboard;
