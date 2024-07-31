// src/components/ListingDetail.js
import React from 'react';
import { useRouter } from 'next/router';

const ListingDetail = ({ listing }) => {
    const router = useRouter();

    const handleEdit = () => {
        router.push(`/listing/edit/${listing.id}`); // Navigate to the edit page
    };

    return (
        <div className="p-4 bg-white rounded shadow">
            <h1 className="text-3xl font-bold mb-4">{listing.title}</h1>
            <image src={listing.photo} alt={listing.title} className="w-full h-auto mb-4 rounded"/>
            <p className="mb-2"><strong>Description:</strong> {listing.description}</p>
            <p className="mb-2"><strong>City:</strong> {listing.address.city}</p>
            <p className="mb-2"><strong>Country:</strong> {listing.address.country}</p>
            <p className="mb-2"><strong>Zip Code:</strong> {listing.address.zipCode}</p>
            <p className="mb-2"><strong>State:</strong> {listing.address.state}</p>
            <p className="mb-2"><strong>Street:</strong> {listing.address.street}</p>
            <p className="mb-2"><strong>Block Number:</strong> {listing.address.blockNumber}</p>
            <p className="mb-2"><strong>Floor Number:</strong> {listing.address.floorNumber}</p>
            <p className="mb-2"><strong>Flat Number:</strong> {listing.address.flatNumber}</p>
            <p className="mb-2"><strong>Net Area:</strong> {listing.netArea} sqm</p>
            <p className="mb-2"><strong>Gross Area:</strong> {listing.grossArea} sqm</p>
            <p className="mb-2"><strong>Built In:</strong> {listing.builtIn}</p>
            <p className="mb-2"><strong>Heating Type:</strong> {listing.heatingType}</p>
            <p className="mb-2"><strong>Bathrooms:</strong> {listing.bathroom}</p>
            <p className="mb-2"><strong>Bedrooms:</strong> {listing.bedroom}</p>
            <p className="mb-2"><strong>Price:</strong> {listing.price}</p>
            <p className="mb-2"><strong>Listing Type:</strong> {listing.listingType}</p>
            <p className="mb-2"><strong>Property Type:</strong> {listing.propertyType}</p>
        </div>
    );
};

export default ListingDetail;
