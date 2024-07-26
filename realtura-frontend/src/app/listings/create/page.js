'use client';
import React, {useState} from 'react';
import {Button, Checkbox, Form, Input, InputNumber, Select, Upload} from 'antd';
import {UploadOutlined} from "@ant-design/icons";
import TextArea from "antd/es/input/TextArea";
import {toast} from "react-toastify";
import {useAuth} from "@/app/context/AuthContext";
import {useRouter} from "next/navigation";
    const App = () => {
        const { userId, logout } = useAuth();
        const router = useRouter();

        const onFinish = async (values) => {
            const updatedValues = { ...values, userId: userId};

            try {
                const response = await fetch('http://localhost:8081/api/v1/listings', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(updatedValues),
                });

                const res = await response.json();
                console.log('resjson:', JSON.stringify(res, null, 2)); // Improved logging

                if (response.ok) {
                    console.log('ok');
                    if (res.status === 'SUCCESS') {
                        console.log('Add Listing successful!');
                        toast.success('Add Listing successful!');
                        router.push("/listings/mylistings")
                    } else {
                        toast.error('Add Listing failed: ' + (data.message || 'Unknown error'));
                    }
                } else {
                    toast.error('Add Listing failed: ' + (data.message || 'Unknown error'));
                }
            } catch (error) {
                toast.error('An error occurred: ' + (error.message || 'Unknown error'));
            }
        }
        const onFinishFailed = (errorInfo) => {
            console.log('Failed:', errorInfo);
        };

        const listingTypes = [
            {value: 'RENTAL', label: 'Rental'},
            {value: 'SALE', label: 'Sale'}
        ];

        const propertyTypes = [
            {value: 'FLAT', label: 'Flat'},
            {value: 'RESIDENCE', label: 'Residence'},
            {value: 'DETACHED_HOUSE', label: 'Detached House'},
            {value: 'FARM_HOUSE', label: 'Farm House'},
            {value: 'MANSION', label: 'Mansion'},
            {value: 'WATERSIDE_HOUSE', label: 'Waterside House'},
            {value: 'WATERSIDE_FLAT', label: 'Waterside Flat'},
            {value: 'SUMMER_HOUSE', label: 'Summer House'},
            {value: 'PREFABRICATED_HOUSE', label: 'Prefabricated House'}
        ];

        const heatingTypes = [
            {value: 'FURNACES', label: 'Furnaces'},
            {value: 'BOILERS', label: 'Boilers'},
            {value: 'HEAT_PUMPS', label: 'Heat Pumps'},
            {value: 'IN_FLOOR', label: 'In-Floor'},
            {value: 'AIR_CONDITIONER', label: 'Air Conditioner'}
        ];
        return (
        <Form
            name="listing"
            labelCol={{span: 8}}
            wrapperCol={{span: 16}}
            style={{maxWidth: 600}}
            initialValues={{remember: true}}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            {/* ... other form items ... */}

            <Form.Item
                label="Title"
                name="title"
                rules={[{required: true, message: 'Please input the title!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Description"
                name="description"
                rules={[{required: true, message: 'Please input the description!'}]}
            >
                <TextArea rows={4}/>
            </Form.Item>

            <Form.Item
                label="City"
                name={['address', 'city']}
                rules={[{required: true, message: 'Please input the city!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Country"
                name={['address', 'country']}
                rules={[{required: true, message: 'Please input the country!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Zip Code"
                name={['address', 'zipCode']}
                rules={[{required: true, message: 'Please input the zip code!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="State"
                name={['address', 'state']}
                rules={[{required: true, message: 'Please input the state!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Street"
                name={['address', 'street']}
                rules={[{required: true, message: 'Please input the street!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Block Number"
                name={['address', 'blockNumber']}
                rules={[{required: true, message: 'Please input the block number!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Floor Number"
                name={['address', 'floorNumber']}
                rules={[{required: true, message: 'Please input the floor number!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Flat Number"
                name={['address', 'flatNumber']}
                rules={[{required: true, message: 'Please input the flat number!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Net Area"
                name="netArea"
                rules={[{required: true, message: 'Please input the net area!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Gross Area"
                name="grossArea"
                rules={[{required: true, message: 'Please input the gross area!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Built In"
                name="builtIn"
                rules={[{required: true, message: 'Please input the built year!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Bathroom"
                name="bathroom"
                rules={[{required: true, message: 'Please input the number of bathrooms!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Bedroom"
                name="bedroom"
                rules={[{required: true, message: 'Please input the number of bedrooms!'}]}
            >
                <InputNumber/>
            </Form.Item>


            <Form.Item
                label="Price"
                name="price"
                rules={[{required: true, message: 'Please input the price!'}]}
            >
                <InputNumber/>
            </Form.Item>

            <Form.Item
                label="Listing Type"
                name="listingType"
                rules={[{required: true, message: 'Please select the listing type!'}]}
            >
                <Select placeholder="Select listing type">
                    {listingTypes.map(type => (
                        <Select.Option key={type.value} value={type.value}>
                            {type.label}
                        </Select.Option>
                    ))}
                </Select>
            </Form.Item>

            <Form.Item
                label="Property Type"
                name="propertyType"
                rules={[{required: true, message: 'Please select the property type!'}]}
            >
                <Select placeholder="Select property type">
                    {propertyTypes.map(type => (
                        <Select.Option key={type.value} value={type.value}>
                            {type.label}
                        </Select.Option>
                    ))}
                </Select>
            </Form.Item>

            <Form.Item
                label="Heating Type"
                name="heatingType"
                rules={[{required: true, message: 'Please select the heating type!'}]}
            >
                <Select placeholder="Select heating type">
                    {heatingTypes.map(type => (
                        <Select.Option key={type.value} value={type.value}>
                            {type.label}
                        </Select.Option>
                    ))}
                </Select>
            </Form.Item>

            <Form.Item
                label="Photo"
                name="photo"
                rules={[{required: true, message: 'Please input the photo url!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item wrapperCol={{offset: 8, span: 16}}>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
        )
    };
    export default App;