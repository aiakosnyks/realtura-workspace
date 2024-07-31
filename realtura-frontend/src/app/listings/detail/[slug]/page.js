// src/pages/detail/[slug]/page.js

'use client';
import React, { useEffect, useState } from 'react';
import ListingDetail from '@/app/components/ListingDetail';
import styles from './page.module.css';

const ListingDetailPage = ({ params }) => {
    const [listing, setListing] = useState(null);
    const slug = params?.slug;

    useEffect(() => {
        const fetchListing = async () => {
            try {
                const res = await fetch(`http://localhost:8081/api/v1/listings/${slug}`);
                if (res.ok) {
                    const data = await res.json();
                    setListing(data.data);
                } else {
                    console.error('Error fetching listing:', res.statusText);
                }
            } catch (error) {
                console.error('Error fetching listing:', error);
            }
        };
        fetchListing();
    }, [slug]);

    return (
        <div className={styles.listingDetails}>
            {listing && <ListingDetail listing={listing} />}
        </div>
    );
};

export default ListingDetailPage;
