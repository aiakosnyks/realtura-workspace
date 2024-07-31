'use client';
import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { Form, Input, Button, InputNumber, Select, message } from 'antd';
import TextArea from "antd/es/input/TextArea";
import {useAuth} from "@/app/context/AuthContext";

const page = 0;
const size = 10;

const UpdateListing = ({ params }) => {
    const { userId, logout } = useAuth();
    const router = useRouter();
    const [form] = Form.useForm(); // Initialize the form instance
    const [listing, setListing] = useState(null);
    const slug = params?.slug;

    useEffect(() => {
        const fetchListing = async () => {
            if (slug) {
                try {
                    const response = await fetch('http://localhost:8081/api/v1/listings/getAllByFilter', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({ page, size, id: slug, userId: userId }),
                    });
                    const res = await response.json();
                    console.log('listings:', JSON.stringify(res.data, null, 2)); // Improved logging
                    if (res.data && res.data.length > 0) {
                        setListing(res.data[0]); // Assuming the data array is not empty
                        form.setFieldsValue(res.data[0]); // Set form values
                    } else {
                        console.error('No listing found');
                    }
                } catch (error) {
                    console.error('Error fetching listings:', error);
                }
            }
        };

        fetchListing();
    }, [slug, form]);

    const onFinish = async (values) => {
        const requestBody = {
            ...values,
            userId: userId
        };
        try {
            const response = await fetch(`http://localhost:8081/api/v1/listings/update/${slug}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestBody),
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

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    if (!listing) return <div>Loading...</div>;

    return (
        <Form
            form={form} // Add form instance here
            name="listing"
            initialValues={listing}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            <Form.Item
                label="Title"
                name="title"
                rules={[{ required: true, message: 'Please input the title!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                label="Description"
                name="description"
                rules={[{ required: true, message: 'Please input the description!' }]}
            >
                <TextArea rows={4} />
            </Form.Item>
            <Form.Item
                label="City"
                name={['address', 'city']}
                rules={[{ required: true, message: 'Please input the city!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                label="Country"
                name={['address', 'country']}
                rules={[{ required: true, message: 'Please input the country!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                label="Zip Code"
                name={['address', 'zipCode']}
                rules={[{ required: true, message: 'Please input the zip code!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                label="State"
                name={['address', 'state']}
                rules={[{ required: true, message: 'Please input the state!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                label="Street"
                name={['address', 'street']}
                rules={[{ required: true, message: 'Please input the street!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                label="Block Number"
                name={['address', 'blockNumber']}
                rules={[{ required: true, message: 'Please input the block number!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Floor Number"
                name={['address', 'floorNumber']}
                rules={[{ required: true, message: 'Please input the floor number!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Flat Number"
                name={['address', 'flatNumber']}
                rules={[{ required: true, message: 'Please input the flat number!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Net Area"
                name="netArea"
                rules={[{ required: true, message: 'Please input the net area!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Gross Area"
                name="grossArea"
                rules={[{ required: true, message: 'Please input the gross area!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Built In"
                name="builtIn"
                rules={[{ required: true, message: 'Please input the built year!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Bathroom"
                name="bathroom"
                rules={[{ required: true, message: 'Please input the number of bathrooms!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Bedroom"
                name="bedroom"
                rules={[{ required: true, message: 'Please input the number of bedrooms!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Price"
                name="price"
                rules={[{ required: true, message: 'Please input the price!' }]}
            >
                <InputNumber />
            </Form.Item>
            <Form.Item
                label="Listing Type"
                name="listingType"
                rules={[{ required: true, message: 'Please select the listing type!' }]}
            >
                <Select placeholder="Select listing type">
                    <Select.Option value="RENTAL">Rental</Select.Option>
                    <Select.Option value="SALE">Sale</Select.Option>
                </Select>
            </Form.Item>
            <Form.Item
                label="Property Type"
                name="propertyType"
                rules={[{ required: true, message: 'Please select the property type!' }]}
            >
                <Select placeholder="Select property type">
                    <Select.Option value="FLAT">Flat</Select.Option>
                    <Select.Option value="RESIDENCE">Residence</Select.Option>
                    <Select.Option value="DETACHED_HOUSE">Detached House</Select.Option>
                    <Select.Option value="FARM_HOUSE">Farm House</Select.Option>
                    <Select.Option value="MANSION">Mansion</Select.Option>
                    <Select.Option value="WATERSIDE_HOUSE">Waterside House</Select.Option>
                    <Select.Option value="WATERSIDE_FLAT">Waterside Flat</Select.Option>
                    <Select.Option value="SUMMER_HOUSE">Summer House</Select.Option>
                    <Select.Option value="PREFABRICATED_HOUSE">Prefabricated House</Select.Option>
                </Select>
            </Form.Item>
            <Form.Item
                label="Heating Type"
                name="heatingType"
                rules={[{ required: true, message: 'Please select the heating type!' }]}
            >
                <Select placeholder="Select heating type">
                    <Select.Option value="FURNACES">Furnaces</Select.Option>
                    <Select.Option value="BOILERS">Boilers</Select.Option>
                    <Select.Option value="HEAT_PUMPS">Heat Pumps</Select.Option>
                    <Select.Option value="IN_FLOOR">In-Floor</Select.Option>
                    <Select.Option value="AIR_CONDITIONER">Air Conditioner</Select.Option>
                </Select>
            </Form.Item>
            <Form.Item
                label="Photo"
                name="photo"
                rules={[{ required: true, message: 'Please input the photo url!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
};

export default UpdateListing;
