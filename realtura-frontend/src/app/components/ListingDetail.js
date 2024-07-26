// src/components/ListingDetail.js
import React from 'react';
import { useRouter } from 'next/router';

const ListingDetail = ({ listing }) => {
    const router = useRouter();

    const handleEdit = () => {
        router.push(`/ad/edit/${listing.id}`); // Navigate to the edit page
    };

    return (
        <div className="p-4 bg-white rounded shadow">
            <h1 className="text-3xl font-bold mb-4">{listing.title}</h1>
            <p className="mb-2"><strong>Description:</strong> {listing.description}</p>
            <p className="mb-2"><strong>Net Area:</strong> {listing.netArea} sqm</p>
            <p className="mb-2"><strong>Gross Area:</strong> {listing.grossArea} sqm</p>
            <p className="mb-2"><strong>Built In:</strong> {listing.builtIn} sqm</p>
            <p className="mb-2"><strong>Heating Type:</strong> {listing.heatingType}</p>
            <p className="mb-2"><strong>Bathrooms:</strong> {listing.bathroom}</p>
            <p className="mb-2"><strong>Bedrooms:</strong> {listing.bedroom}</p>
            <p className="mb-2"><strong>Property Type:</strong> {listing.propertyType}</p>
            <p className="mb-2"><strong>Listing Type:</strong> {listing.listingType}</p>
            <p className={`font-semibold ${listing.active ? 'text-green-500' : 'text-red-500'}`}>
                {listing.active ? 'Active' : 'Inactive'}
            </p>
            <button
                onClick={handleEdit}
                className="mt-4 px-4 py-2 text-white bg-blue-500 rounded"
            >
                Edit
            </button>
        </div>
    );
};

export default ListingDetail;
