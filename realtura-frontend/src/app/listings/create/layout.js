import {Navbar} from "@nextui-org/react";
import {AuthProvider} from "@/app/context/AuthContext";

export const metadata = {
    title: "Create Next App",
    description: "Generated by create next app",
};

export default function ListingCreateLayout({ children }) {
    return (
        <div>
            {children}
        </div>
    );
}
