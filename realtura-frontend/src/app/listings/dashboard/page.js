import ListingCard from "@/app/components/ListingCard";
import styles from './page.module.css'
import ListingCardReadOnly from "@/app/components/ListingCardReadOnly";
import FilterColumn from "@/app/components/FilterColumn";

const page = 0;
const size = 10;
const fetchListings = async () => {
    try {
        const response = await fetch('http://localhost:8081/api/v1/listings/getAllByFilter', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Cache-Control': 'no-cache'
            },
            body: JSON.stringify({ page, size}),
        });
        const res =await response.json();
        console.log('listings:', JSON.stringify(res.data, null, 2)); // Improved logging
        return res.data;
    } catch (error) {
        console.error('Error fetching listings:', error);
    }
};

const Dashboard = async () => {
    const listings = await fetchListings();
    console.log(listings);
    return (
        <div>
        <div className={styles.main} >
            <h1 className={styles.description}>İlanlar</h1>
            <div className={styles.card}>
                {listings && listings.map((listing) => (
                    <ListingCardReadOnly key={listing.id} listing={listing} />
                ))}
            </div>
        </div>
        <FilterColumn />
        </div>
    );
};

export default Dashboard;
